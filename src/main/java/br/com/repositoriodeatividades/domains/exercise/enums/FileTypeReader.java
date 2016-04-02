package br.com.repositoriodeatividades.domains.exercise.enums;

import br.com.repositoriodeatividades.domains.exercise.PdfReader;
import br.com.repositoriodeatividades.domains.exercise.WordDocReader;
import br.com.repositoriodeatividades.domains.interfaces.Readable;


public enum FileTypeReader {

    PDF(new PdfReader()),
    MSWORD(new WordDocReader()),
    DOCX(new WordDocReader()),
    DOC(new WordDocReader());

    private Readable readable;

    FileTypeReader(Readable readable) {
        this.readable = readable;
    }

    public Readable getReader() {
        return readable;
    }
}
