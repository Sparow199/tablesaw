package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.store.ColumnMetadata;

import java.util.UUID;

/**
 *
 */
abstract class AbstractColumn implements Column {

  private String id;

  private String name;

  private String comment;

  public AbstractColumn(String name) {
    this.name = name;
    this.id = UUID.randomUUID().toString();
  }

  public AbstractColumn(ColumnMetadata metadata) {
    this.name = metadata.getName();
    this.id = metadata.getId();
  }

  public String name() {
    return name;
  }

  public String id() {
    return id;
  }

  @Override
  public String metadata() {
    return columnMetadata().toJson();
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract void addCell(String stringvalue);

  @Override
  public String comment() {
    return comment;
  }

  @Override
  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public ColumnMetadata columnMetadata() {
    return new ColumnMetadata(this);
  }
}