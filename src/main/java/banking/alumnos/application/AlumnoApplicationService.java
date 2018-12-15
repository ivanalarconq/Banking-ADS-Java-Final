package banking.alumnos.application;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.users.application.dto.UserDto;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.common.infrastructure.security.Hashing;
import banking.common.infrastructure.security.JwtTokenProvider;
import banking.users.application.dto.UserAuthDto;
import banking.users.application.dto.UserClaimDto;
import banking.users.domain.entity.User;
import banking.users.domain.entity.UserClaim;
import banking.users.domain.repository.UserClaimRepository;
import banking.users.domain.repository.UserRepository;

@Service
public class AlumnoApplicationService {
	@Autowired
	private AlumnoRepository alumnoRepository;
		
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
    
	public AlumnoDto create(AlumnoDto alumnoDto) {
		Notification notification = this.createValidation(alumnoDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		String hashPassword = Hashing.hash(alumnoDto.getPassword());
		alumnoDto.setPassword(hashPassword);
		Alumno alumno = mapper.map(alumnoDto, Alumno.class);
		alumno = alumnoRepository.save(alumno);
		alumnoDto = mapper.map(alumno, AlumnoDto.class);
        return alumnoDto;
    }
	
	private Notification createValidation(alumnoDto alumnoDto) {
		Notification notification = new Notification();
		if (alumnoDto == null || alumnoDto.getName().equals(RequestBodyType.INVALID.toString())) {
			notification.addError("Invalid JSON data in request body.");
		}
		Alumno alumno = alumnoRepository.getByName(alumnoDto.getName().trim());
		if (alumno != null) {
			notification.addError("Alumno name is already registered");
		}
		return notification;
	}
	
	
	private AlumnoAuthDto buildUserAuthDto(Alumno authAlumno) throws Exception {
		AlumnoAuthDto alumnoAuthDto = new AlumnoAuthDto();
		alumnoAuthDto.setId(authAlumno.getId());
		alumnoAuthDto.setName(authAlumno.getName());
		alumnoAuthDto.setAuthenticated(true);
		alumnoAuthDto.setBearerToken(new UUID(0L, 0L).toString());

		alumnoAuthDto.setBearerToken(jwtTokenProvider.buildJwtToken(alumnoAuthDto));
		return alumnoAuthDto;
	}
	
	public AlumnoDto get(long alumnoId) {
		ModelMapper modelMapper = new ModelMapper();
		User alumno = this.alumnoRepository.getById(alumnoId);
		UserDto alumnoDto = modelMapper.map(alumno, AlumnoDto.class);
        return alumnoDto;
    }
    
    public List<AlumnoDto> getPaginated(int page, int pageSize) {
		Notification notification = this.getPaginatedValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<Alumno> alumnos = this.alumnoRepository.getPaginated(page, pageSize);
		List<AlumnoDto> alumnosDto = mapper.map(alumnos, new TypeToken<List<AlumnoDto>>() {}.getType());
        return alumnosDto;
    }
    
    private Notification getPaginatedValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}
}
