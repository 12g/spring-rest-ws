package cl.blm.examples.spring.rest.controllers;

import cl.blm.examples.spring.AppGlobals;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import cl.blm.examples.spring.rest.model.dto.UserDto;
import cl.blm.examples.spring.rest.services.CrudService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * API point of entry for anything user-related.
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@RestController
@RequestMapping("/api")
public class UsersController {
  private final static Logger LOG = LoggerFactory.getLogger(UsersController.class);

  @Autowired private CrudService<UserDto, Long> userSvc;

  @GetMapping("/users")
  public Collection<UserDto> read(@RequestParam Map<String, String> allRequestParams) {
    return this.read(null, null, allRequestParams);
  }

  @GetMapping("/users/{pageSize}")
  public Collection<UserDto> read(@PathVariable Integer requestPageSize,
          @RequestParam Map<String, String> allRequestParams) {
    return this.read(requestPageSize, null, allRequestParams);
  }

  /**
   * Retrieve a page of users.
   * Si el URL tenía un query string (RequestParam, lo transforma a un Map, genera un Predicate a partir
   * de él y filtra la búsqueda con este Predicate.
   *
   * @param requestPageSize
   * @param requestPageIndex
   * @param allRequestParams Un Map conteniendo una colección pares nombre/valor.
   *
   * @see RequestParam
   * @see Predicate
   * @return Una colección de objetos EmpleadoDTO
   */
  @GetMapping("/users/{requestPageSize}/{requestPageIndex}")
  public Collection<UserDto> read(@PathVariable Integer requestPageSize,
          @PathVariable Integer requestPageIndex,
          @RequestParam Map<String, String> allRequestParams) {
    LOG.info("read");

    Integer pageSize = AppGlobals.ITEMS_PER_PAGE;
    Integer pageIndex = 0;
    Predicate filters = null;

    if (requestPageIndex != null && requestPageIndex > 0) {
      pageSize = requestPageIndex;
    }
    if (requestPageIndex != null && requestPageIndex > 0) {
      pageIndex = requestPageIndex - 1;
    }
    if (allRequestParams != null && !allRequestParams.isEmpty()) {
      filters = userSvc.queryParamsMapToPredicate(allRequestParams);
    }

    Collection<UserDto> usuarios = userSvc.read(pageSize, pageIndex, filters);
    return usuarios;
  }

  /**
   * Almacena un Usuario nuevo o actualiza uno existente.
   *
   * @param dto Un objeto DTO representando el Usuario a almacenar/actualizar.
   *
   * @return El ID del usuario.
   */
  @PostMapping("/user")
  public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
    LOG.info("create");
    LOG.debug("dto={}", dto);
    if (dto != null) {
      if (dto.getUserId() != null) {
        return ResponseEntity.badRequest().build();
      } else {
        dto = userSvc.create(dto);
        LOG.debug("dto={}" + dto);
        return ResponseEntity.ok(dto);
      }
    }
    return ResponseEntity.ok(null);
  }

  /**
   * Elimina un Usuario de la base de datos.
   *
   * @param userId
   * @param dto
   *
   * @return true si la operación fue exitosa, false si no lo fue.
   * @throws javassist.NotFoundException
   */
  @PutMapping("/user/{id}")
  public ResponseEntity<UserDto> update(@RequestParam("id") Long userId,
          @RequestBody UserDto dto)
          throws NotFoundException {
    LOG.info("update");
    LOG.debug("userId={}, dto={}", userId, dto);
    if (dto == null || userId == null || userId == 0L || !userId.equals(dto.getUserId())) {
      return ResponseEntity.badRequest().body(dto);
    } else {
      UserDto foundUser = userSvc.find(userId);
      if (foundUser.equals(dto)) {
        return ResponseEntity.ok(dto);
      } else {
        dto = userSvc.update(dto);
        LOG.debug("dto={}" + dto);
        return ResponseEntity.ok(dto);
      }
    }
  }

  /**
   * Deletes an User.
   *
   * @param userId The user ID to delete.
   *
   * @return true si la operación fue exitosa, false si no lo fue.
   */
  @DeleteMapping("/user/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable("id") long userId) {
    LOG.info("delete");
    LOG.debug("usuarioId={}", userId);
    if (userId != 0L) {
      boolean deleted = userSvc.delete(userId);
      return ResponseEntity.ok(deleted);
    }
    return ResponseEntity.ok(false);
  }

  /**
   * Elimina un Usuario de la base de datos.
   *
   * @param userId
   * @param dto
   *
   * @return true si la operación fue exitosa, false si no lo fue.
   * @throws javassist.NotFoundException
   */
  @GetMapping("/user/{id}")
  public ResponseEntity<UserDto> find(@RequestParam("id") Long userId,
          @RequestBody UserDto dto)
          throws NotFoundException {
    LOG.info("update");
    LOG.debug("userId={}, dto={}", userId, dto);
    if (dto == null || userId == null || userId == 0L || !userId.equals(dto.getUserId())) {
      return ResponseEntity.badRequest().body(dto);
    } else {
      UserDto foundUser = userSvc.find(userId);
      if (foundUser.equals(dto)) {
        return ResponseEntity.ok(dto);
      } else {
        dto = userSvc.update(dto);
        LOG.debug("dto={}" + dto);
        return ResponseEntity.ok(dto);
      }
    }
  }
}
