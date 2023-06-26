package com.sistema.ventas.Services;


import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Repositories.LineaVentaRepository;
import com.sistema.ventas.Repositories.ProductoRepository;
import com.sistema.ventas.Repositories.VentaRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VentaService {

    //inyeccion de dependecias

    private  VentaRepository ventaRepository;
    private LineaVentaRepository lineaVentaRepository;



    public VentaService(VentaRepository ventaRepository,LineaVentaRepository lineaVentaRepository,ProductoRepository productoRepository){
        this.ventaRepository=ventaRepository;
        this.lineaVentaRepository=lineaVentaRepository;
    }

    //crear un venta
    public Venta createNewVenta(LineaVenta lineaVenta) throws ServiceException {
        try {

            log.info("VentaService:createNewVenta ejecucion iniciada.");
            Venta venta = new Venta(LocalDate.now().minusDays(1));
            venta.getLineaVentas().add(lineaVenta);

            log.info("VentaService:createNewVenta ejecucion finalizada.\n");
            return ventaRepository.save(venta);
        } catch (Exception ex) {
            System.out.println(ex);
            throw  new ServiceException("Ha ocurrido un problema al crear una venta en el servicio");
        }
    }

    public List<Venta> getVentas() throws ServiceException {
        try{
            log.info("VentaService:getVentas ejecucion iniciada.");
            List<Venta> ventas= ventaRepository.findAll();
            log.debug("VentaService:getProducts devolviendo ventas de la base de datos {}", ValueMapper.jsonAsString(ventas));
            log.info("VentaService:getVentas ejecucion finalizada\n.");

            return ventas;
        } catch ( Exception ex){
            log.error("error lanzado:", ex.getMessage());
            throw new ServiceException("Ha ocurrido un problema al mostrar las ventas en el servicio");
        }

    }


    public Optional<Venta> findByFecha(LocalDate fecha) throws ServiceException{
        log.info("VentaService:findByFecha ejecucion iniciada.");
        Optional<Venta> findVenta=ventaRepository.findByfechaCreacion(fecha);
        log.info("VentaService:findByFecha ejecucion finalizada\n.");
        return Optional.ofNullable(findVenta.orElse(null));
    }

}
