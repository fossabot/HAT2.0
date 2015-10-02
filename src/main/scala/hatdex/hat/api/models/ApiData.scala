package hatdex.hat.api.models

import hatdex.hat.dal.Tables.{DataFieldRow, DataRecordRow, DataTableRow, DataValueRow}
import org.joda.time.LocalDateTime

case class ApiDataField(
    id: Option[Int],
    dateCreated: Option[LocalDateTime],
    lastUpdated: Option[LocalDateTime],
    tableId: Option[Int],
    name: String,
    values: Option[Seq[ApiDataValue]])

object ApiDataField {
  def fromDataField(field: DataFieldRow) = {
    ApiDataField(
      Some(field.id), Some(field.dateCreated), Some(field.lastUpdated),
      Some(field.tableIdFk), field.name, None
    )
  }
}

case class ApiDataRecord(
    id: Option[Int],
    dateCreated: Option[LocalDateTime],
    lastUpdated: Option[LocalDateTime],
    name: String,
    tables: Option[Seq[ApiDataTable]])

object ApiDataRecord {
  def fromDataRecord(record: DataRecordRow)(tables: Option[Seq[ApiDataTable]]) = {
    new ApiDataRecord(
      Some(record.id), Some(record.dateCreated), Some(record.lastUpdated),
      record.name, tables
    )
  }
}

case class ApiDataTable(
    id: Option[Int],
    dateCreated: Option[LocalDateTime],
    lastUpdated: Option[LocalDateTime],
    name: String,
    source: String,
    fields: Option[Seq[ApiDataField]],
    subTables: Option[Seq[ApiDataTable]])

object ApiDataTable {
  def fromDataTable(table: DataTableRow)(fields: Option[Seq[ApiDataField]])(subTables: Option[Seq[ApiDataTable]]) = {
    new ApiDataTable(
      Some(table.id),
      Some(table.dateCreated),
      Some(table.lastUpdated),
      table.name,
      table.sourceName,
      fields,
      subTables
    )
  }
}


case class ApiDataValue(
    id: Option[Int],
    dateCreated: Option[LocalDateTime],
    lastUpdated: Option[LocalDateTime],
    value: String,
    fieldId: Int,
    recordId: Int)

object ApiDataValue {
  def fromDataValue(value: DataValueRow) = {
    new ApiDataValue(
      Some(value.id), Some(value.dateCreated), Some(value.lastUpdated),
      value.value, value.fieldId, value.recordId
    )
  }
}
