package builders;

import br.com.entity.Profissional;

public class CriadorDeProfissional {
	private Profissional profissional = new Profissional();
	
	public Profissional addProfissional(String nome, String cpf){
		profissional.setNome(nome);
		profissional.setCpf(cpf);
		return profissional;
	}
	
}
