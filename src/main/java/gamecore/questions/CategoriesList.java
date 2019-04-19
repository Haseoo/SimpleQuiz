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
    public Category(int id, String name, String filePath) {
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

    private static void initCategoriesList() throws CategoriesListImproperFormatted {
        int i = 0;
        try {
            File xmlCList = new File(categoriesListFilePath);
            Document XMLCtgList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlCList);

            XMLCtgList.getDocumentElement().normalize();
            NodeList XMLCategoriesNode = XMLCtgList.getElementsByTagName("category");

            for (i = 0; i < XMLCategoriesNode.getLength(); i++) {
                Node XMLCategoryNode = XMLCategoriesNode.item(i);
                if (XMLCategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element XMLCategoryElement = (Element) XMLCategoryNode;
                    String categoryName = XMLCategoryElement.getElementsByTagName("name").item(0).getTextContent();
                    String categoryFilePath = "src/main/resources/questions/" + XMLCategoryElement.getElementsByTagName("filePath").item(0).getTextContent();
                    categories.add(new Category(i, categoryName, categoryFilePath));
                    }
                }
        } catch (Exception e) {
            String label = String.format("Category no.%d is improperly formatted.", i);
            CategoriesListImproperFormatted exception = new CategoriesListImproperFormatted(label);
            exception.initCause(e.getCause());
            throw exception;
        }
    }

    private static void fillCategory(Category i) {
        try {
            File categoryFile = new File(i.getFilePath());
            Document XMLCategory = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(categoryFile);
            XMLCategory.normalizeDocument();
            NodeList XMLQuestionsList = XMLCategory.getElementsByTagName("question");
            i.setNumberOfQuestions(XMLQuestionsList.getLength());
        } catch (Exception e) {
            String label = String.format("Category %s is improperly formatted : %s", i.getName(), e.getCause());
            System.out.println(label);
            categories.remove(i);
        }
    }

    private static void fillCategoriesList() {
        ArrayList<Category> tmp = (ArrayList<Category>)categories.clone();
        for (Category i : tmp) {
            fillCategory(i);
        }
    }

    public static void initList() throws CategoriesListImproperFormatted{
        if (categories == null) {
            categories = new ArrayList<>();
            initCategoriesList();
            fillCategoriesList();
        }
        for (Category it : categories) {
            System.out.println(it.getName() + ": " + it.getNumberOfQuestions());
        }
    }

    public static void resetNumberUsedQuestions() {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }
        for(Category i : categories) {
            i.setNumberOfQuestions(0);
        }
    }

    static public String getCategoryName(int categoryID) {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }

        int index = categories.indexOf(new Category(categoryID, "", ""));

        return categories.get(index).getName();
    }

    static public String getCategoryPath(int categoryID) {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }

        int index = categories.indexOf(new Category(categoryID, "", ""));

        return categories.get(index).getFilePath();
    }

    static public void increaseNumberOfUsedQuestions(int categoryID) {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }

        int index = categories.indexOf(new Category(categoryID, "", ""));
        int usedQuestions = categories.get(index).getNumberOfUsedQuestions();
        categories.get(index).setNumberOfQuestions(usedQuestions + 1);
    }

    static public int getNumberOfCategories() {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }
        return categories.size();
    }

    static public int getNumberOfQuestions(int categoryNo) {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }
        return categories.get(categoryNo).getNumberOfQuestions();
    }

    static public int getTotalNumberOfQuestions() {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }
        int sum = 0;
        for (Category i : categories) {
            sum += i.getNumberOfQuestions();
        }
        return sum;
    }

    public static  void main(String...args) {
        try {
            CategoriesList.initList();
        } catch(Exception e) {
            System.out.println(e + ":" +  e.getCause());
        }
    }
}
