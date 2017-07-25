package br.com.veppo.estante.model.leitura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.veppo.estante.model.AbstractEntity;
import br.com.veppo.estante.model.livro.Livro;


@Entity
@Table(name="LE_LEITURA_LEIT")
public class Leitura extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LEITURA_LEIT")
	private Long id;
	
	@ManyToOne
	@Column(name = "ID_LIVRO_LIVR")
	private Livro livro;
	
	@Column(name = "DT_CONCLUSAO_LIVR")
	private Date dataDeConclusao;
	
	@Column(name = "NU_TEMPO_DE_LEITURA_LIVR")
	private Long tempoDeLeitura;
	
	@Column(name = "DS_COMENTARIO_LEIT")
	private String comentario;
	
	@Column(name = "VL_NOTA_LEIT")
	private Long nota;
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Date getDataDeConclusao() {
		return dataDeConclusao;
	}
	public void setDataDeConclusao(Date dataDeConclusao) {
		this.dataDeConclusao = dataDeConclusao;
	}
	public Long getTempoDeLeitura() {
		return tempoDeLeitura;
	}
	public void setTempoDeLeitura(Long tempoDeLeitura) {
		this.tempoDeLeitura = tempoDeLeitura;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Long getNota() {
		return nota;
	}
	public void setNota(Long nota) {
		this.nota = nota;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dataDeConclusao == null) ? 0 : dataDeConclusao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result + ((tempoDeLeitura == null) ? 0 : tempoDeLeitura.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leitura other = (Leitura) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (dataDeConclusao == null) {
			if (other.dataDeConclusao != null)
				return false;
		} else if (!dataDeConclusao.equals(other.dataDeConclusao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (tempoDeLeitura == null) {
			if (other.tempoDeLeitura != null)
				return false;
		} else if (!tempoDeLeitura.equals(other.tempoDeLeitura))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Leitura [id=" + id + ", livro=" + livro + ", dataDeConclusao=" + dataDeConclusao + ", tempoDeLeitura="
				+ tempoDeLeitura + ", comentario=" + comentario + ", nota=" + nota + "]";
	}
}
