package gamecore.questions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class CategoriesList {
    private int[] numberOfUsedQuestions;

    private static ArrayList<Category> categories = null;
    public static final String categoriesListFilePath = "questions/categoriesList.xml";

    public CategoriesList() throws CategoriesListImproperFormatted {
        initList();
        numberOfUsedQuestions = new int[categories.size()];
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



    private static void fillCategory(Category i) throws Exception {
        File categoryFile = new File(i.getFilePath());
        Document XMLCategory = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(categoryFile);
        XMLCategory.normalizeDocument();
        NodeList XMLQuestionsList = XMLCategory.getElementsByTagName("question");
        i.setNumberOfQuestions(XMLQuestionsList.getLength());
    }

    private static void fillCategoriesList() {
        Iterator<Category> it =  categories.iterator();
        while(it.hasNext()) {
            Category current = it.next();
            try {
                fillCategory(current);
            } catch(Exception e) {
                String label = String.format("Category's file %s is improperly formatted : %s", current.getName(), e.getCause());
                System.out.println(label);
                it.remove();
            }
        }
    }

    private static void initList() throws CategoriesListImproperFormatted{
        if (categories == null) {
            categories = new ArrayList<>();
            initCategoriesList();
            fillCategoriesList();
        }
        for (Category it : categories) {
            System.out.println(it.getName() + ": " + it.getNumberOfQuestions());
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

    public void increaseNumberOfUsedQuestions(int categoryID) {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }

        int index = categories.indexOf(new Category(categoryID, "", ""));
        numberOfUsedQuestions[index]++;
    }

    public int getNumberOfCategories() {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }
        return categories.size();
    }

    public int getNumberOfQuestions(int categoryNo) {
        if (categories == null) {
            throw new NullPointerException("An attempt to access categories list before initialization.");
        }
        return numberOfUsedQuestions[categoryNo];
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
            CategoriesList ct = new CategoriesList();
            for(Category it : categories) {
                System.out.println(it);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
