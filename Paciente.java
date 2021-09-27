
package Consultorio;

import java.util.Date;

public class Paciente {
	private String cpfP;
	private String nome;
	private Date dataNascimento;
	private Date dataCadastro;
	
	public String getCpfP() {
		return cpfP;
	}
	public void setCpfP(String cpfP) {
		this.cpfP = cpfP;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Paciente(String cpf, String nome, Date dataNascimento, Date dataCadastro) {
		this.cpfP = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
	}
	public Paciente() {
		// TODO Auto-generated constructor stub
	}
	

	
}
