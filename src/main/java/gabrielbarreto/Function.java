package gabrielbarreto;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Function {

    private CidadeDAO dao = new CidadeDAO();

    @FunctionName("funcaocriarcidade")
    public Cidade criar(@HttpTrigger(name = "restcriarcidade", methods = {HttpMethod.POST}, route = "cidade") Cidade cidade) {
        return dao.criar(cidade);
    }
    
    @FunctionName("funcaolercidade")
    public List<Cidade> ler(@HttpTrigger(name = "restlercidade", methods = {HttpMethod.GET}, route = "cidade") String x) {
        return dao.ler();
    }
    
    @FunctionName("funcaoalterarcidade")
    public Cidade alterar(@HttpTrigger(name = "restalterarcidade", methods = {HttpMethod.PUT}, route = "cidade") Cidade c) {
        return dao.alterar(c);
    }
    
    @FunctionName("funcaodeletarcidade")
    public int criar(@HttpTrigger(name = "restdeletarcidade", methods = {HttpMethod.DELETE}, route = "cidade/{id}") @BindingName("id") Long id) {
        //System.out.println(String.format("ID: %d", id));
        return 200;
    }
}

@Data
class Cidade {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Estado estado;

    public Cidade(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}

@Data
class Estado {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nome;

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}

class CidadeDAO {

    public Cidade criar(Cidade cidade) {
        cidade.setId(new Long(1));
        return cidade;
    }

    public Cidade alterar(Cidade cidade) {
        cidade.setNome("Marilia");
        return cidade;
    }

    public List<Cidade> ler() {
        return Stream.of(
                new Cidade(1L, "Cornélio Procópio", new Estado(1L, "Paraná")),
                new Cidade(2L, "Ourinhos", new Estado(2L, "São Paulo")),
                new Cidade(3L, "Teresina", new Estado(3L, "Piauí"))
        ).collect(Collectors.toList());
    }

    public int apagar(int id) {
        return 200;
    }
}