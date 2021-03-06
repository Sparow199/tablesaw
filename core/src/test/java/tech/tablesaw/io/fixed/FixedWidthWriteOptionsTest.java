package tech.tablesaw.io.fixed;

import org.junit.Test;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class FixedWidthWriteOptionsTest {

    @Test
    public void testSettingsPropagation() {

        Table test = Table.create("test", StringColumn.create("t"));
        test.stringColumn(0).appendCell("testing");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        FixedWidthWriteOptions options = new FixedWidthWriteOptions.Builder(stream)
                .header(true)
                .lineSeparatorString("\r\n")
                .padding('~')
                .build();

        assertTrue(options.header());

        FixedWidthWriter writer = new FixedWidthWriter(test, options);
        assertTrue(writer.getHeader());
        assertEquals("\r\n", writer.getFormat().getLineSeparatorString());
        assertEquals('~', writer.getFormat().getPadding());
    }

    @Test
    public void testSettingsAutoConfigurationPropagation() {

        Table test = Table.create("test", StringColumn.create("t"));
        test.stringColumn(0).appendCell("testing");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        FixedWidthWriteOptions options = new FixedWidthWriteOptions.Builder(stream)
                .autoConfigurationEnabled(true)
                .build();

        assertTrue(options.autoConfigurationEnabled());

        assertTrue(options.header());

        FixedWidthWriter writer = new FixedWidthWriter(test, options);
        assertTrue(writer.getHeader());
    }


}
