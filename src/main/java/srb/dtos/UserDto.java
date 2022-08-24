package srb.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class UserDto {

	@NotBlank
	private String nome;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private Date date;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}


