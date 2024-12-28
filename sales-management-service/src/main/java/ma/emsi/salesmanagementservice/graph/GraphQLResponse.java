package ma.emsi.salesmanagementservice.graph;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GraphQLResponse<T> {
    private T data;

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }
}
