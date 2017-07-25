package br.com.veppo.estante.model.futurasaquisicoes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.veppo.estante.model.AbstractEntity;
import br.com.veppo.estante.model.autor.Autor;
import br.com.veppo.estante.model.genero.Genero;


@Entity
@Table(name="FA_FUTURAS_AQUISICOES_FUAQ")
public class FuturasAquisicoes extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_FUTURAS_AQUISICOES_FUAQ")
	private Long id;
	
	@Column(name = "NM_TITULO_FUAQ")
	private String titulo;

	@ManyToOne
	@Column(name = "ID_AUTOR_PESS")
	private Autor autor;
	
	@ManyToOne
	@Column(name = "ID_GENERO_PRIMEIRO_GENE")
	private Genero genero1;
	
	@ManyToOne
	@Column(name = "ID_GENERO_SEGUNDO_GENE")
	private Genero genero2;
	
	@ManyToOne
	@Column(name = "ID_GENERO_TERCEIRO_GENE")
	private Genero genero3;
	
	@Column(name = "DS_COMENTARIO_FUAQ")
	private String comentario;
	
	@Column(name = "VL_PRECO_ESTIMADO_FUAQ")
	private double precoEstimado;
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Genero getGenero1() {
		return genero1;
	}
	public void setGenero1(Genero genero1) {
		this.genero1 = genero1;
	}
	public Genero getGenero2() {
		return genero2;
	}
	public void setGenero2(Genero genero2) {
		this.genero2 = genero2;
	}
	public Genero getGenero3() {
		return genero3;
	}
	public void setGenero3(Genero genero3) {
		this.genero3 = genero3;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public double getPrecoEstimado() {
		return precoEstimado;
	}
	public void setPrecoEstimado(double precoEstimado) {
		this.precoEstimado = precoEstimado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((genero1 == null) ? 0 : genero1.hashCode());
		result = prime * result + ((genero2 == null) ? 0 : genero2.hashCode());
		result = prime * result + ((genero3 == null) ? 0 : genero3.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoEstimado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		FuturasAquisicoes other = (FuturasAquisicoes) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (genero1 == null) {
			if (other.genero1 != null)
				return false;
		} else if (!genero1.equals(other.genero1))
			return false;
		if (genero2 == null) {
			if (other.genero2 != null)
				return false;
		} else if (!genero2.equals(other.genero2))
			return false;
		if (genero3 == null) {
			if (other.genero3 != null)
				return false;
		} else if (!genero3.equals(other.genero3))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(precoEstimado) != Double.doubleToLongBits(other.precoEstimado))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "FuturasAquisicoes [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", genero1=" + genero1
				+ ", genero2=" + genero2 + ", genero3=" + genero3 + ", comentario=" + comentario + ", precoEstimado="
				+ precoEstimado + "]";
	}
}
