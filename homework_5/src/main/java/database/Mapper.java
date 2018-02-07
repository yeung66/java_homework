package database;

import vo.Program;

import java.util.List;

public interface Mapper {

    Program findById(String Id);

    void insertProgram(Program p);

    List<Program> findPrograms(String text);

    void updateById(Program p);

    void deleteById(String id);
}
