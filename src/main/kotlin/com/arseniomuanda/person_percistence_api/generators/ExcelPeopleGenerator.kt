package com.arseniomuanda.person_percistence_api.generators

import com.arseniomuanda.person_percistence_api.domains.models.PersonModel
import jakarta.servlet.http.HttpServletResponse
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFFont
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.IOException

class ExcelPeopleGenerator(private val people: MutableList<PersonModel>) {

    private var workbook: XSSFWorkbook = XSSFWorkbook()
    private var sheet: Sheet = workbook.createSheet("People")

    private fun createHeader() {
        val row: Row = sheet.createRow(0)
        val style: CellStyle = workbook.createCellStyle()
        val font: XSSFFont = workbook.createFont()
        font.bold = true
        font.fontHeightInPoints = 16
        style.setFont(font)

        createCell(row, 0, "Nome", style)
        createCell(row, 1, "Email", style)
        createCell(row, 2, "Phone", style)
        createCell(row, 3, "Address", style)
        createCell(row, 4, "City", style)
        createCell(row, 5, "Country", style)
        createCell(row, 6, "ID", style)
        createCell(row, 7, "Passport", style)
    }

    private fun createCell(row: Row, columnCount: Int, valueOfCell: Any, style: CellStyle) {
        val cell: Cell = row.createCell(columnCount)

        when (valueOfCell) {
            is Int -> cell.setCellValue(valueOfCell.toDouble())
            is Long -> cell.setCellValue(valueOfCell.toDouble())
            is String -> cell.setCellValue(valueOfCell)
            is Boolean -> cell.setCellValue(valueOfCell)
            else -> cell.setCellValue(valueOfCell.toString())  // Para casos genéricos
        }
        cell.cellStyle = style
        sheet.autoSizeColumn(columnCount)  // Ajustar as larguras das colunas após o preenchimento
    }

    private fun writeBody() {
        val style: CellStyle = workbook.createCellStyle()
        val font = workbook.createFont()
        font.fontHeightInPoints = 14
        style.setFont(font)

        var rowCount = 1
        for (record in people) {
            val row: Row = sheet.createRow(rowCount++)
            var columnCount = 0

            createCell(row, columnCount++, record.fullName, style)
            createCell(row, columnCount++, record.user.email, style)
            createCell(row, columnCount++, record.phone, style)
            createCell(row, columnCount++, "${record.country}, ${record.city}", style)
            createCell(row, columnCount++, record.city, style)
            createCell(row, columnCount++, record.country, style)
            createCell(row, columnCount++, record.idNumber, style)
            createCell(row, columnCount++, record.passport ?: "", style)  // Uso do operador Elvis (?:)
        }
    }

    @Throws(IOException::class)
    fun generateExcelFile(response: HttpServletResponse) {
        createHeader()
        writeBody()

        // Usando 'use' para garantir o fechamento automático do outputStream
        response.outputStream.use { outputStream ->
            workbook.write(outputStream)
        }

        workbook.close()  // Fechar o workbook ao final
    }
}
