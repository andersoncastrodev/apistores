package br.com.asoft.apistores.dtointerface;

/*
    IMPORTANTE
    Usando para Customizar as consulta feitas no Repository de Pessoa.
 */
public interface PessoaNome {

    // getNome() porque é o metodo get do atributo "nome da classe PESSOA"
    // Tem que ser exatamente do mesmo jeito.
    String getNome();
    String getTelefone();

}
