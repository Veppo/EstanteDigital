package br.com.veppo.estante.enums.messages;

public enum EDMessages {
    ESTANTE("br.com.veppo.messages.estante");

    private final String basename;

    EDMessages(String basename) {
        this.basename = basename;
    }

    /**
     * Retorna o pacote da mensagem 
     * 
     * @return
     */
    public String getBasename() {
        return basename;
    }
}