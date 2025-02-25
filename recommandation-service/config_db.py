from sqlalchemy import Float, create_engine, Column, Integer, String, Text, BigInteger
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# Configuration de la connexion
DATABASE_URL = ""
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# Modèle de données
class Book(Base):
    __tablename__ = "book"
    
    id = Column(Integer, primary_key=True, index=True)
    isbn13 = Column(BigInteger)  # ISBN-13 est un entier
    isbn10 = Column(String(255))  # ISBN-10 est une chaîne de caractères
    title = Column(String(255))  # Le titre est une chaîne de caractères
    subtitle = Column(String(255))  # Le sous-titre est une chaîne de caractères
    authors = Column(String(255))  # Les auteurs (sous forme de chaîne)
    categories = Column(String(255))  # Catégories (chaîne ou liste au format JSON)
    thumbnail = Column(Text)  # Lien vers l'image (URL)
    description = Column(Text)  # La description (texte long)
    published_year = Column(Float)  # Année de publication (flottant si incertain)
    average_rating = Column(Float)  # Moyenne des évaluations
    num_pages = Column(Float)  # Nombre de pages (flottant si incertain)
    ratings_count = Column(Float)  # Nombre de notes
    textual_representation = Column(Text)  # Représentation textuelle

# Modèle de données
class Categories(Base):
    __tablename__ = "categories"
    
    id = Column(Integer, primary_key=True, index=True)
    designation = Column(String(255))

# Création des tables
Base.metadata.create_all(bind=engine)