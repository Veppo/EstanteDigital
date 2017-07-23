package br.com.veppo.model.livro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.veppo.model.AbstractEntity;
import br.com.veppo.model.autor.Autor;
import br.com.veppo.model.editora.Editora;
import br.com.veppo.model.genero.Genero;


@Entity
@Table(name = "LI_LIVRO_LIVR")
public class Livro extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LIVRO_LIVR")
	private Long id;
	
	@Column(name = "NM_TITULO_LIVR")
	private String titulo;
	
	@Column(name = "NU_PAGINAS_LIVR")
	private Long paginas;
	
	@ManyToOne
	@Column(name = "ID_GENERO_PRIMEIRO_GENE")
	private Genero genero1;
	
	@ManyToOne
	@Column(name = "ID_GENERO_SEGUNDO_GENE")
	private Genero genero2;
	
	@ManyToOne
	@Column(name = "ID_GENERO_TERCEIRO_GENE")
	private Genero genero3;
	
	@ManyToOne
	@Column(name = "ID_AUTOR_PESS")
	private Autor autor;
	
	@ManyToOne
	@Column(name = "ID_EDITORA_EDIT")
	private Editora editora;
	
	@Column(name = "DT_COMPRA_LIVR")
	private Date dataDeCompra;
	
	@Column(name = "DS_COMENTARIO_LIVR")
	private String comentario;
	
	@Column(name = "VL_PRECO_LIVR")
	private double preco;
	
	@Column(name = "FL_POCKET_LIVR")
	private boolean pocket;
	
	@Column(name = "FL_XEROX_LIVR")
	private boolean xerox;
	
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
	public Long getPaginas() {
		return paginas;
	}
	public void setPaginas(Long paginas) {
		this.paginas = paginas;
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
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public Date getDataDeCompra() {
		return dataDeCompra;
	}
	public void setDataDeCompra(Date dataDeCompra) {
		this.dataDeCompra = dataDeCompra;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public boolean isPocket() {
		return pocket;
	}
	public void setPocket(boolean pocket) {
		this.pocket = pocket;
	}
	public boolean isXerox() {
		return xerox;
	}
	public void setXerox(boolean xerox) {
		this.xerox = xerox;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dataDeCompra == null) ? 0 : dataDeCompra.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((genero1 == null) ? 0 : genero1.hashCode());
		result = prime * result + ((genero2 == null) ? 0 : genero2.hashCode());
		result = prime * result + ((genero3 == null) ? 0 : genero3.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paginas == null) ? 0 : paginas.hashCode());
		result = prime * result + (pocket ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + (xerox ? 1231 : 1237);
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
		Livro other = (Livro) obj;
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
		if (dataDeCompra == null) {
			if (other.dataDeCompra != null)
				return false;
		} else if (!dataDeCompra.equals(other.dataDeCompra))
			return false;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
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
		if (paginas == null) {
			if (other.paginas != null)
				return false;
		} else if (!paginas.equals(other.paginas))
			return false;
		if (pocket != other.pocket)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (xerox != other.xerox)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", paginas=" + paginas + ", genero1=" + genero1 + ", genero2="
				+ genero2 + ", genero3=" + genero3 + ", autor=" + autor + ", editora=" + editora + "]";
	}
}
