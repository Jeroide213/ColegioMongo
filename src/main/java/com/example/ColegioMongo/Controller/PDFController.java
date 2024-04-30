package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.PDF;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PDFController {
    @PostMapping("/guardarDocumento")
    public String guardarDocumento(@RequestParam("archivoPdf") MultipartFile archivoPdf) {
        if (!archivoPdf.isEmpty()) {
            try {
                byte[] contenidoPdf = archivoPdf.getBytes();
                PDF pdf = new PDF();
                pdf.setContenidoPdf(contenidoPdf);
                return "Documento guardado exitosamente";
            } catch (IOException e) {
                e.printStackTrace();
                return "Error al saveStudent el documento: " + e.getMessage();
            }
        } else {
            return "El archivo PDF está vacío";
        }
    }
}
