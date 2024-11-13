package br.com.fatec;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ProdutoDto {
    @NotNull(message = "O nome não pode ser null!")
    @NotEmpty(message = "o nome não pode ser vazio!")
    private String nome;
    @NotNull(message = "A descricao não pode ser null!")
    @NotEmpty(message = "A descricao não pode ser vazias")
    private String descricao;
    @NotNull(message = "O preço não pode ser null!")
    private Double preco;
}
