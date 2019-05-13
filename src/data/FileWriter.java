package data;

import business.MenuItem;
import business.Order;
import business.Restaurant;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

public class FileWriter {
        public FileWriter(Restaurant restaurant, Order order) {
                PdfPTable table = new PdfPTable(2);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell("Name");
                table.addCell("Price");
                table.setHeaderRows(1);
                PdfPCell[] cells = table.getRow(0).getCells();
                for (int j=0;j<cells.length;j++){
                        cells[j].setBackgroundColor(BaseColor.GRAY);
                }
                Document document = new Document();
                try {
                        PdfWriter.getInstance(document, new FileOutputStream("bill.pdf"));
                } catch (DocumentException | FileNotFoundException e) {
                        e.printStackTrace();
                }
                document.open();
                HashSet<MenuItem> menuItems = new HashSet<>();
                menuItems = restaurant.getMap().get(order);
                int price = 0;
                for(MenuItem m:menuItems)
                {
                        table.addCell(m.getName());
                        table.addCell(m.getPrice()+"");
                        price += m.getPrice();
                }
                try {
                        document.add(table);
                        document.add(new Paragraph("Total price: " + price));
                } catch (DocumentException e) {
                        e.printStackTrace();
                }
                document.close();
        }
}
