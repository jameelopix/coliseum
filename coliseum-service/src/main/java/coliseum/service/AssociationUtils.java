package coliseum.service;

import java.util.List;

import coliseum.jpa.Association;

public class AssociationUtils {
    public static void createAssociation(List<Association> associations, String fieldName, boolean neededFlag) {
        if (neededFlag) {
            Association association = new Association();
            association.setFieldName(fieldName);
            associations.add(association);
        }
    }
}