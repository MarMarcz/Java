package org.example.tools.geographies;

import org.example.model.Geography;
import org.example.tools.abstractions.IParse;

public class GeographyParser implements IParse <Geography> {

    @Override
    public Geography parse(String line) {
        line = line.trim();
        Geography geography = new Geography();
        String[] tab = line.split(";");
        geography.setId(Integer.parseInt(tab[0]));
        geography.setType(tab[1]);
        geography.setName(tab[2]);
        geography.setCode(tab[3]);
        if (tab[4].equals("NULL")) {

        }else
        geography.setParentId(Integer.parseInt(tab[4]));

        return geography;
    }
}
