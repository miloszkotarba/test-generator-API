package com.mkotarba.first_crud_api.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.mkotarba.first_crud_api.collection.Test;
import com.mkotarba.first_crud_api.collection.Exercise;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response, Test test) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font addintialInfoFont = FontFactory.getFont(FontFactory.HELVETICA, "Cp1250", 12);
        Paragraph additionalInfo = new Paragraph("Imię i nazwisko: ", addintialInfoFont);
        additionalInfo.setAlignment(Element.ALIGN_LEFT);
        document.add(additionalInfo);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, "Cp1250", 16);
        Paragraph title = new Paragraph(test.getName(), font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        if (test.getDuration() != 0) {
            Paragraph duration = new Paragraph("(Czas trwania: " + test.getDuration() + " min.)", addintialInfoFont);
            duration.setAlignment(Element.ALIGN_CENTER);
            document.add(duration);

        }

        document.add(Chunk.NEWLINE);

        Font exerciseNoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, "Cp1250", 13);
        exerciseNoFont.setColor(2, 99, 170);

        // Dodanie pytań
        int questionNo = 1;
        for (Exercise exercise : test.getExercises()) {
            document.add(new Paragraph("Zadanie " + questionNo + ".", exerciseNoFont));
            document.add(new Paragraph(exercise.getContent(), FontFactory.getFont(FontFactory.HELVETICA, "Cp1250", 13)));
            document.add(Chunk.NEWLINE);
            questionNo++;
        }

        document.close();
    }
}