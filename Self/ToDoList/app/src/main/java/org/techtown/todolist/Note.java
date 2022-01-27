package org.techtown.todolist;

public class Note {
//    _id는 데이터베이스를 만들 때 만든 _id대로 정렬할 때 쓰고, todo는 EditText에 입력한 것들을 저장할 때 씁니다.
    int _id;
    String todo;

    public Note(int _id, String todo) {
        this._id = _id;
        this.todo = todo;
    }

    public int get_id() {
        return _id;
    }

    public String getTodo() {
        return todo;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
