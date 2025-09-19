package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.kaven.Cafeteria.dominio.exception.EstudianteNoExisteException;
import org.kaven.Cafeteria.dominio.exception.EstudianteYaExisteException;
import org.kaven.Cafeteria.dominio.repository.EstudianteRepository;
import org.kaven.Cafeteria.persistence.crud.CrudEstudianteEntity;
import org.kaven.Cafeteria.persistence.entity.EstudianteEntity;
import org.kaven.Cafeteria.web.mapper.EstudianteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudianteEntityRepository implements EstudianteRepository {
    private final CrudEstudianteEntity crudEstudiante;
    private final EstudianteMapper estudianteMapper;

    public EstudianteEntityRepository(CrudEstudianteEntity crudEstudiante, EstudianteMapper estudianteMapper) {
        this.crudEstudiante = crudEstudiante;
        this.estudianteMapper = estudianteMapper;
    }

    @Override
    public List<EstudianteDto> obtenerTodoEstudiante() {
        return this.estudianteMapper.toDto(this.crudEstudiante.findAll());
    }

    @Override
    public EstudianteDto obtenerEstudiantePorCodigo(Long codigo) {
        EstudianteEntity estudianteEntity = this.crudEstudiante.findById(codigo)
                .orElseThrow(() -> new EstudianteNoExisteException(codigo));
        return this.estudianteMapper.toDto(estudianteEntity);
    }

    @Override
    public EstudianteDto guardarEstudiante(EstudianteDto estudianteDto) {
        if (this.crudEstudiante.findFirstByNombre(estudianteDto.name()) != null) {
            throw new EstudianteYaExisteException(estudianteDto.name());
        }

        EstudianteEntity estudiante = this.estudianteMapper.toEntity(estudianteDto);
        EstudianteEntity estudianteGuardado = this.crudEstudiante.save(estudiante);
        return this.estudianteMapper.toDto(estudianteGuardado);
    }

    @Override
    public EstudianteDto modificarEstudiante(Long codigo, ModEstudianteDto modEstudianteDto) {
        EstudianteEntity estudianteEntity = this.crudEstudiante.findById(codigo)
                .orElseThrow(() -> new EstudianteNoExisteException(codigo));

        this.estudianteMapper.modificarEntityFromDto(modEstudianteDto, estudianteEntity);
        EstudianteEntity estudianteActualizado = this.crudEstudiante.save(estudianteEntity);
        return this.estudianteMapper.toDto(estudianteActualizado);
    }

    @Override
    public void eliminarEstudiante(Long codigo) {
        EstudianteEntity estudianteEntity = this.crudEstudiante.findById(codigo)
                .orElseThrow(() -> new EstudianteNoExisteException(codigo));
        this.crudEstudiante.deleteById(codigo);
    }
}