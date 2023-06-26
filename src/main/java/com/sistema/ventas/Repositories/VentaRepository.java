package com.sistema.ventas.Repositories;

import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {


}