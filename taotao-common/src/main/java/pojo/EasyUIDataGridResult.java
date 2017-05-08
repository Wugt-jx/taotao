package pojo;

import java.util.List;


public class EasyUIDataGridResult<T> {
    private Long total;
    private List<T> rows;

    public EasyUIDataGridResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDataGridResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
