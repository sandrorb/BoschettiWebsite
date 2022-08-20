package srb.testewebsrb.dtos;

import javax.validation.constraints.NotBlank;

public class UserDto {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}


