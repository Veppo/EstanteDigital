package br.com.veppo.estante.model.scheduler;

import org.joda.time.DateTime;

public class Scheduler {

    private Long id;
    private String nome;
    private String classe;
    private String descricao;
    private DateTime dtInicioExecucao;
    private DateTime dtFimExecucao;
    private String segundo;
    private String minuto;
    private String hora;
    private String diaDaSemana;
    private String diaDoMes;
    private String mes;
    private String ano;
    private boolean flHabilitado;
    private Integer timeout;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public DateTime getDtInicioExecucao() {
		return dtInicioExecucao;
	}

	public void setDtInicioExecucao(DateTime dtInicioExecucao) {
		this.dtInicioExecucao = dtInicioExecucao;
	}

	public DateTime getDtFimExecucao() {
		return dtFimExecucao;
	}

	public void setDtFimExecucao(DateTime dtfimExecucao) {
		this.dtFimExecucao = dtfimExecucao;
	}

	public String getSegundo() {
		return segundo;
	}

	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}

	public String getMinuto() {
		return minuto;
	}

	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public String getDiaDoMes() {
		return diaDoMes;
	}

	public void setDiaDoMes(String diaDoMes) {
		this.diaDoMes = diaDoMes;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public boolean getFlHabilitado() {
		return flHabilitado;
	}

	public void setFlHabilitado(boolean flHabilitado) {
		this.flHabilitado = flHabilitado;
	}
	
	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	/*
     * Expression of the schedule set in the object
     */
    public String getExpression()
    {
        return "sec=" + segundo + ";min=" + minuto + ";hour=" + hora
                + ";dayOfMonth=" + diaDoMes + ";month=" + mes + ";year=" + ano
                + ";dayOfWeek=" + diaDaSemana;
    }

    @Override
    public boolean equals(Object anotherObj) {
        if (anotherObj instanceof Scheduler) {
            return id.equals(((Scheduler) anotherObj).id);
        }
        return false;
    }

    @Override
    public String toString() {
        return id + "-" + nome + "-" + classe;
    }
}