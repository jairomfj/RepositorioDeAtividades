package br.com.repositoriodeatividades.domains.enums;

import br.com.repositoriodeatividades.domains.readers.PdfReader;
import br.com.repositoriodeatividades.domains.readers.WordDocReader;
import br.com.repositoriodeatividades.domains.interfaces.Readable;


public enum FileReaderType {

    PDF(new PdfReader()),
    MSWORD(new WordDocReader()),
    DOCX(new WordDocReader()),
    DOC(new WordDocReader());

    private Readable readable;

    FileReaderType(Readable readable) {
        this.readable = readable;
    }

    public Readable getReader() {
        return readable;
    }
}
