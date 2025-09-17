package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.persistence.crud.CrudEstudianteEntity;
import org.kaven.Cafeteria.persistence.entity.EstudianteEntity;
import org.kaven.Cafeteria.web.mapper.EstudianteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudianteEntityRepository {
    private final CrudEstudianteEntity crudEstudiante;
    private final EstudianteMapper estudianteMapper;


    public EstudianteEntityRepository(CrudEstudianteEntity crudEstudiante, EstudianteMapper estudianteMapper) {
        this.crudEstudiante = crudEstudiante;
        this.estudianteMapper = estudianteMapper;
    }

    @Override
    public List<EstudianteDto> obtenerTodo() {
        return this.estudianteMapper.toDto(this.crudEstudiante.findAll());
    }

    @Override
    public EstudianteDto obtenerEstudiantePorCodigo(Long codigo) {
        EstudianteEntity estudianteEntity = this.crudEstudiante.findById(codigo).orElse(null);
        return this.estudianteMapper.toDto(this.crudEstudiante.findById(codigo).orElse(null));
        //Falta la excepcion aaaaaaaaa
        /*if (estudianteEntity == null) throw new EstudianteNoExisteException(codigo);
        else {
            return this.estudianteMapper.toDto(this.crudEstudiante.findById(codigo).orElse(null));
        }*/
    }

    @Override
    public EstudianteDto guardarEstudiante(EstudianteDto estudianteDto) {
        //Falta la excepcion aaaaaaaaa
        if (this.crudEstudiante.findFirstByNombre(estudianteDto.name() != null)){
            //throw new EstudianteYaExisteException(estudianteDto.name());
        }

        EstudianteEntity estudiante = this.estudianteMapper.toEntity(estudianteDto);
        this.crudEstudiante.save(estudiante);
        return this.estudianteMapper.toDto(estudiante);
    }


    @Override
    public EstudianteDto modificarEstudiante(EstudianteDto estudianteDto) {
        EstudianteEntity estudiante = this.estudianteMapper.toEntity(estudianteDto);
        this.crudEstudiante.save(estudiante);
        return this.estudianteMapper.toDto(this.crudEstudiante.save.(EstudianteEntity));
        //Cuando este la excepcion hacer el if
    }


    @Override
    public void eliminarEstudiante(Long codigo) {
        EstudianteEntity estudianteEntity = this.crudEstudiante.findById(codigo).orElse(null);
        // Hacer if para la excepcion
        this.crudEstudiante.findById(codigo);
    }
}
