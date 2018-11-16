package com.faust8888.pdf.parser.servcie;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PdfParser {

    private PDDocument document;
    private int threshold;

    public byte[] parse (final PDDocument document, final int threshold) throws IOException {
        this.document = document;
        this.threshold = threshold;
        int startPage = 1;
        int endPage = document.getNumberOfPages();

        return new ForkJoinPool().invoke(new PdfParseRecursiveTask(startPage, endPage));
    }

    class PdfParseRecursiveTask extends RecursiveTask<byte[]> {

        private PDFTextStripper textStripper;
        private final int startPage;
        private final int endPage;

        PdfParseRecursiveTask(final int startPage, final int endPage) throws IOException {
            super();
            this.textStripper = new PDFTextStripper();
            this.startPage = startPage;
            this.endPage = endPage;
        }

        @Override
        protected byte[] compute() {
            try {
                int range = endPage - (startPage - 1);
                if(range > threshold) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    Collection<PdfParseRecursiveTask> subTasks = ForkJoinTask.invokeAll(createSubTasks());
                    subTasks.forEach(subTask -> subTask.fork());
                    subTasks.forEach(subTask -> {
                        try {
                            byteArrayOutputStream.write(subTask.join());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    return byteArrayOutputStream.toByteArray();
                } else {
                    return processing();
                }
            } catch (Throwable ex) {
                return new byte[0];
            }
        }

        private Collection<PdfParseRecursiveTask> createSubTasks() throws IOException {
            DescriptiveStatistics statistics = new DescriptiveStatistics();
            List<Integer> range = IntStream.range(startPage, (endPage + 1)).boxed().collect(Collectors.toList());
            range.stream().forEach(pageNumber -> statistics.addValue(pageNumber));

            int middlePage = Double.valueOf(statistics.getPercentile(50)).intValue();

            List<PdfParseRecursiveTask> subTasks = new ArrayList<>(2);
            subTasks.add(new PdfParseRecursiveTask(startPage, middlePage));
            subTasks.add(new PdfParseRecursiveTask((middlePage + 1), endPage));

            return subTasks;
        }

        private byte[] processing() throws IOException {
            textStripper.setStartPage(startPage);
            textStripper.setEndPage(endPage);
            return textStripper.getText(document).getBytes();
        }
    }

}
