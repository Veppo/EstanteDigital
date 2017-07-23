package br.com.veppo.model.messages;

public enum EDMessages {
    ESTANTE("br.com.veppo.messages.estante");

    private final String basename;

    EDMessages(String basename) {
        this.basename = basename;
    }

    /**
     * Retorna o pacote do Bundle
     * 
     * @return
     */
    public String getBasename() {
        return basename;
    }
}