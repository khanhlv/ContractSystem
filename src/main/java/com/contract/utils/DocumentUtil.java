package com.contract.utils;

import java.io.File;
import java.util.Map;
import java.util.StringTokenizer;

import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DocumentUtil {

    private static final Logger logger = LoggerFactory.getLogger(DocumentUtil.class);
    /**
     * VariableReplace ${colour}, ${icecream}
     *
     * @param fileInput
     * @param fileOutput
     * @param save
     * @return
     */
    public static void variableReplace(Map dataVar, String fileInput, String fileOutput) {
        try {
            // Exclude context init from timing
            Context.getWmlObjectFactory();

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                    .load(new File(fileInput));

            VariablePrepare.prepare(wordMLPackage);

            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

            documentPart.variableReplace(dataVar);

            Docx4J.save(wordMLPackage, new File(fileOutput));
        } catch (Exception ex) {
            logger.error("Variable Replace", ex);
        }
    }

    public static String newlineToBreakHack(String r) {
        StringTokenizer st = new StringTokenizer(r, "\n\r\f"); // tokenize on the newline character, the carriage-return character, and the form-feed character
        StringBuilder sb = new StringBuilder();

        boolean firsttoken = true;
        while (st.hasMoreTokens()) {
            String line = (String) st.nextToken();
            if (firsttoken) {
                firsttoken = false;
            } else {
                sb.append("</w:t><w:br/><w:t>");
            }
            sb.append(line);
        }
        return sb.toString();
    }
}
