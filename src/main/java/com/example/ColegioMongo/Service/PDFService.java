package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.PDF;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PDFService {
    public void guardarDocumento(MultipartFile archivoPdf) {
        if (!archivoPdf.isEmpty()) {
            try {
                byte[] contenidoPdf = archivoPdf.getBytes();
                PDF pdf = new PDF();
                pdf.setContenidoPdf(contenidoPdf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
