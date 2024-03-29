package br.com.repositoriodeatividades.usecases.exercise.utils.enums;

import br.com.repositoriodeatividades.usecases.exercise.imports.models.PdfReader;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.TxtReader;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.WordDocReader;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.Readable;


public enum FileReaderType {

    TXT(new TxtReader()),
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
