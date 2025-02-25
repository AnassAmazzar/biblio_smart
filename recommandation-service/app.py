import math
from config_db import Book, SessionLocal
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Dict, Any
import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


class RecommendationService:
    def __init__(self):
        self.session = SessionLocal()
        self.load_data()

    def load_data(self):
        products = self.session.query(Book).all()
        self.data = pd.DataFrame([p.__dict__ for p in products])
        self.vectorizer = TfidfVectorizer(stop_words='english')
        self.tfidf_matrix = self.vectorizer.fit_transform(self.data['description'].fillna(''))
    
    def get_recommendations(self, product_id: int, top_n: int = 5) -> List[Dict['str', Any]]:
        """Génère des recommandations basées sur la similarité cosinus"""
        try:
            recommendations = []
            for rec in self._raw_recommendations(product_id, top_n):
                safe_rec = {}
                for k, v in rec.items():
                    if isinstance(v, (int, float, np.integer, np.floating)):
                        # Gestion explicite des valeurs numériques
                        if math.isfinite(float(v)):
                            safe_rec[k] = float(v)
                        else:
                            safe_rec[k] = None
                    else:
                        safe_rec[k] = v
                recommendations.append(safe_rec)
            return recommendations
        except Exception as e:
            print(f"Erreur de recommandation : {e}")
            return []
    
    def _raw_recommendations(self, product_id: int, top_n: int) -> List[Dict]:
        try:
            # Trouver l'index du produit
            index = self.data[self.data['isbn13'] == product_id].index[0]
            
            # Calculer similarités cosinus
            similarities = cosine_similarity(
                self.tfidf_matrix[index], 
                self.tfidf_matrix
            ).flatten()
            
            # Exclure le produit actuel et trier
            similarities[index] = -1  # Marquer pour exclusion
            top_indices = similarities.argsort()[-top_n-1:-1][::-1]
            
            # Générer recommandations
            recommendations = self.data.iloc[top_indices].to_dict('records')
            
            return recommendations
    
        except IndexError:
            print(f"Produit {product_id} non trouvé")
            return []
        # try:
        #     index = self.data[self.data['isbn13'] == product_id].index[0]
        #     similarities = cosine_similarity(self.tfidf_matrix[index], self.tfidf_matrix).flatten()
        #     top_indices = similarities.argsort()[-top_n-1:-1][::-1]
            
        #     recommendations = self.data.iloc[top_indices].to_dict('records')
        #     return recommendations
        # except Exception as e:
        #     print(e)
        #     raise HTTPException(status_code=404, detail=str(e))

app = FastAPI(title="Service de Recommandation")
#recommendation_service = RecommendationService('../books_dataset_100.csv')
recommendation_service = RecommendationService()

# # Conversion explicite des types numériques
# def process_recommendations(recommendations):
#     processed = []
#     for rec in recommendations:
#         # Convertir les types potentiellement problématiques
#         safe_rec = {
#             k: float(v) if isinstance(v, (int, float)) and not math.isnan(v) else v 
#             for k, v in rec.items()
#         }
#         processed.append(safe_rec)
#     return processed

@app.get("/recommendations/books/{product_id}")
def get_recommendations(product_id: int, top_n: int = 12):
    raw_recommendation = recommendation_service.get_recommendations(product_id, top_n)
    return raw_recommendation

@app.get("/recommendations/books")
def getAllBooks():
    session = SessionLocal()
    books = session.query(Book).limit(20).all()
    return books

@app.get("/recommendations/book/{product_id}")
def getBookById(product_id: int):
    session = SessionLocal()
    book = session.get(Book, product_id)
    return book
    

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8840)

#print(get_recommendations(9780002005883))