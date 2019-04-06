package gamecore.questions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Question {
    public final String questionContent;
    public final String[] answers;
    public final int correctAnswer;

    Question(QuestionCord questionCord) {
        String contentTMP = null;
        String[] answersTMP = null;
        int answerTMP = 0;

        String CategoryFileName = CategoriesList.getCategoryPath(questionCord.getNumberOfCategory());
        int questionID = questionCord.getNumberOfQueston();
        try {
            File CategoryXMLFile = new File(CategoryFileName);

            Document XMLCategory = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(CategoryXMLFile);
            XMLCategory.normalizeDocument();

            NodeList XMLQuestionsList = XMLCategory.getElementsByTagName("question");

            Node questionNode = XMLQuestionsList.item(questionID);

            if (questionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element question = (Element)questionNode;
                Node contentNode = question.getElementsByTagName("content").item(0);
                contentTMP = contentNode.getTextContent();

                NodeList answersXML  = question.getElementsByTagName("answer");

                answersTMP = new String[answersXML.getLength()];

                for(int i = 0; i < answersXML.getLength(); i++) {
                    Node answerNode = answersXML.item(i);
                    answersTMP[i] = answerNode.getTextContent();
                }

                Node answerNode = question.getElementsByTagName("correctAnswer").item(0);
                answerTMP = Integer.parseInt(answerNode.getTextContent());

            }

        }catch(Exception e) {
            //TODO throw an exception about category file
            System.out.println("Invalid file format exception!");
        }

        questionContent = contentTMP;
        answers = answersTMP;
        correctAnswer = answerTMP;
    }

    static public void main(String...args) {
        CategoriesList.initList();

        QuestionCord qc = new QuestionCord(0,1);
        Question q = new Question(qc);
        System.out.printf("Q:%s\n", q.questionContent);
        for(String a : q.answers) {
            System.out.println(a);
        }
        System.out.printf("O:%d\n", q.correctAnswer);
    }
}
