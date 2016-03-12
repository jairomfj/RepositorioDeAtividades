package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PdfReaderTests {

    @Autowired
    PdfReader pdfReader;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void shouldExtractFileContent() throws Exception {
        Resource resource = applicationContext.getResource("classpath:exercise_enumerated_by_number_choices_enumerated_by_alphabet.pdf");
        String content = pdfReader.read(resource.getFile());
        String[] splittedContent = content.split("\n");

        assertEquals("1) Assinale a alternativa que contém o plural correto dos seguintes substantivos:",splittedContent[0].trim());
        assertEquals("monkey - appendix - story - shrimp - tomato - proof",splittedContent[1].trim());
        assertEquals("a) monkeys - appendixes - stories - shrimps - tomatos - prooves",splittedContent[2].trim());
        assertEquals("b) monkies - appendices - storys - shrimp - tomatoes - proofes",splittedContent[3].trim());
        assertEquals("c) monkeys - appendixies - stories - shrimps - tomatoes - proofs",splittedContent[4].trim());
        assertEquals("d) monkeys - appendices - stories - shrimp - tomatoes - proofs",splittedContent[5].trim());
        assertEquals("e) monkies - appendixes - storys - shrimps - tomatos - prooves",splittedContent[6].trim());
    }

}
