package com.faust8888.cambridge.parser.service;

import com.faust8888.cambridge.parser.item.Word;
import com.faust8888.cambridge.parser.item.WordTranslation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import com.faust8888.cambridge.parser.item.WordMeaning;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public final class CambridgePageParserService {

    private static final String WORD_ELEMENT = "hw";
    private static final String BODY_ELEMENT = "pos-body";
    private static final String FORM_ELEMENT = "pos";
    private static final String MEANING_ELEMENT = "guideword";
    private static final String PRE_MEANING_ELEMENT = "def";
    private static final String SHORT_MEANING_ELEMENT = "def-body";
    private static final String EXAMPLE_ELEMENT = "examp";

    private static final String CAMBRIDGE_DICTIONARY_URL = "https://dictionary.cambridge.org/dictionary/english/";

    private String word;

    public Word parse(final String word, final int timeoutMillis) throws IOException {
        this.word = Objects.requireNonNull(word, "Word's parameter can'readPdf be null.");

        Document wordDoc = Jsoup.parse(new URL(CAMBRIDGE_DICTIONARY_URL + this.word), timeoutMillis);
        List<WordTranslation> wordTranslations = parseBodyElements(wordDoc.getElementsByClass(BODY_ELEMENT));
        return new Word(this.word, wordTranslations);
    }

    private String getWordElementValue(final Element wordElement) {
        List<Node> childNodes = wordElement.childNodes();
        Node wordNode = getFirstItem(childNodes);
        return wordNode != null ? wordNode.outerHtml() : null;
    }

    private String getFormElementValue(final Element wordElement) {
        Elements formElements = getParentElements(wordElement, FORM_ELEMENT);
        Element formElement = getFirstItem(formElements);
        return formElement != null ? formElement.text() : null;
    }

    private String getShortMeaningElementValue(final Element wordElement) {
        Elements meaningParentElements = getParentElements(wordElement, MEANING_ELEMENT);
        for (Element meaningParentElement : meaningParentElements) {
            for (Node meaningNode : meaningParentElement.childNodes()) {
                if (meaningNode instanceof Element) {
                    return ((Element) meaningNode).text();
                }
            }
        }
        return null;
    }

    private List<WordTranslation> parseBodyElements(final Elements bodyElements) {
        List<WordTranslation> result = new ArrayList<>();
        for (Element bodyElement : bodyElements) {
            Elements wordElements = bodyElement.getElementsByClass(WORD_ELEMENT);
            List<WordTranslation> wordTranslations = parseWordElements(wordElements);
            result.addAll(wordTranslations);
        }
        return result;
    }

    private List<WordTranslation> parseWordElements(final Elements wordElements) {
        List<WordTranslation> result = new ArrayList<>();
        for (Element wordElement : wordElements) {
            WordTranslation wordTranslation = parseWordElement(wordElement);
            if (wordTranslation != null) result.add(wordTranslation);
        }
        return result;
    }

    private WordTranslation parseWordElement(final Element wordElement) {
        String word = getWordElementValue(wordElement);
        String form = getFormElementValue(wordElement);
        String shortMeaning = getShortMeaningElementValue(wordElement);
        List<WordMeaning> meanings = getMeaningsElementValue(wordElement);
        if (word != null && form != null && shortMeaning != null) {
            return new WordTranslation(word, shortMeaning, form, meanings);
        }
        return null;
    }

    private List<WordMeaning> getMeaningsElementValue(final Element wordElement) {
        List<WordMeaning> meanings = new ArrayList<>();

        Element preMeaningElement = wordElement.parent() != null ? wordElement.parent().parent() : null;
        if (preMeaningElement != null && preMeaningElement.getElementsByClass(PRE_MEANING_ELEMENT) != null) {
            Elements shortMeaningElements = preMeaningElement.getElementsByClass(SHORT_MEANING_ELEMENT);
            for (Element shortMeaningElement : shortMeaningElements) {
                List<Node> childMeaningNodes = shortMeaningElement.childNodes();
                List<String> examples = new ArrayList<>();
                for (Node childMeaning : childMeaningNodes) {
                    if (childMeaning instanceof Element) {
                        if (((Element) childMeaning).classNames().contains(EXAMPLE_ELEMENT)) {
                            examples.add(((Element) childMeaning).text());
                        }
                    }
                }
                Element parentElement = shortMeaningElement.parent();
                String explanation = parentElement.text().contains(":")
                        ? parentElement.text().substring(0, parentElement.text().indexOf(":"))
                        : parentElement.text();

                WordMeaning wordMeaning = new WordMeaning(this.word, explanation, examples);
                meanings.add(wordMeaning);
            }
        }
        return meanings;
    }

    private Elements getParentElements(final Element wordElement, final String className) {
        Element parentNode = wordElement.parent();
        return parentNode != null ? parentNode.getElementsByClass(className) : null;
    }

    private <T> T getFirstItem(List<T> list) {
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

