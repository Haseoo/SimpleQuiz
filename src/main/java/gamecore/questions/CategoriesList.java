package gamecore.questions;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.util.ArrayList;

final class Category {
    private int id,
                numberOfQuestions,
                numberOfUsedQuestions;
    private String  name,
                    filePath;
    Category(int id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getID() {
        return id;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfUsedQuestions() {
        return numberOfUsedQuestions;
    }

    public void setNumberOfUsedQuestions(int numberOfUsedQuestions) {
        this.numberOfUsedQuestions = numberOfUsedQuestions;
    }

    @Override
    public boolean equals(Object other) {
        if(this.getClass() != other.getClass()) {
            return false;
        }

        Category otherObject = (Category)other;

        return  (otherObject.id == this.id);
    }

    @Override
    public int hashCode() {
        return 7 * id;
    }
}

public class CategoriesList {
    private static ArrayList<Category> categories = null;
    public static final String categoriesListFilePath = "src/main/resources/questions/categoriesList.xml";

    private CategoriesList() {
    }

    private static void fillCategoriesList() {
        for (Category i : categories) {
            try {
                File categoryFile = new File(i.getFilePath());
                Document XMLCategory = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(categoryFile);
                XMLCategory.normalizeDocument();
                NodeList XMLQuestionsList = XMLCategory.getElementsByTagName("question");
                i.setNumberOfQuestions(XMLQuestionsList.getLength());
                System.out.println(i.getName() + ": " + i.getNumberOfQuestions());
            } catch (Exception e) {
                System.out.println("In file: " + i.getFilePath() + e);
            }
        }
    }

       private static void initCategoriesList(){
            try {
                File xmlCList = new File(categoriesListFilePath);
                Document XMLCtgList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlCList);

                XMLCtgList.getDocumentElement().normalize();

                NodeList XMLCategoriesNode = XMLCtgList.getElementsByTagName("category");

                for (int i = 0; i < XMLCategoriesNode.getLength(); i++) {
                    Node XMLCategoryNode = XMLCategoriesNode.item(i);
                    if (XMLCategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                        try {
                            Element XMLCategoryElement = (Element) XMLCategoryNode;
                            String categoryName = XMLCategoryElement.getElementsByTagName("name").item(0).getTextContent();
                            String categoryFilePath = "src/main/resources/questions/" + XMLCategoryElement.getElementsByTagName("filePath").item(0).getTextContent();

                            categories.add(new Category(i + 1, categoryName, categoryFilePath));
                        } catch(NullPointerException e) {
                            //TODO Invalid format exception
                            System.out.println("Invalid file format exception!");
                            return;
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    public static void initList() {
        if (categories == null) {
            categories = new ArrayList<>();
            initCategoriesList();
            fillCategoriesList();
        }
    }

    static public String getCategoryName(int categoryID) {
        if (categories == null) {
            //TODO List not initialized exception
        }

        int index = categories.indexOf(new Category(categoryID, "", ""));

        return categories.get(index).getName();
    }

    static public String getCategoryPath(int categoryID) {
        if (categories == null) {
            //TODO List not initialized exception
        }

        int index = categories.indexOf(new Category(categoryID, "", ""));

        return categories.get(index).getFilePath();
    }

    public static  void main(String...args) {
        CategoriesList.initList();
    }
}
