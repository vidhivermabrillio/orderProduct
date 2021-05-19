package com.verizon.product.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.verizon.product.model.Product;

@Component
public class ExcelUploadUtil {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "ProductName", "ProductType", "ProductCost", "OrderId" };
	static String SHEET = "ProductDetails";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static ByteArrayInputStream productsToExcel(List<Product> products) {

		try {
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			{
			}
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (Product product : products) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(product.getId());
				row.createCell(1).setCellValue(product.getProductName());
				row.createCell(2).setCellValue(product.getProductType());
				row.createCell(3).setCellValue(product.getProductCost());

			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static List<Product> excelToProducts(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Product> products = new ArrayList<Product>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Product product = new Product();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						product.setId((int) currentCell.getNumericCellValue());
						break;

					case 1:
						product.setProductName(currentCell.getStringCellValue());
						break;

					case 2:
						product.setProductType(currentCell.getStringCellValue());
						break;

					case 3:
						product.setProductCost((int) currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				products.add(product);
			}

			workbook.close();

			return products;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
