package com.safetynet.alerts.utils;

import java.io.*;

public class CopyJsonFile {

    public static void copyJsonFile() throws IOException {
        File originalFile = new File("src/main/resources/originalData.json");
        File destinationFile = new File("src/main/resources/data.json");

        // Vérifier si le fichier source existe
        if (!originalFile.exists()) {
            throw new FileNotFoundException("Le fichier source " + originalFile + " n'existe pas.");
        }

        // Créer un flux de lecture pour le fichier source
        FileReader fileReader = new FileReader(originalFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Créer un flux d'écriture pour le fichier de destination
        FileWriter fileWriter = new FileWriter(destinationFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Copier le contenu ligne par ligne
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        // Fermer les flux
        bufferedReader.close();
        bufferedWriter.close();
    }

}

