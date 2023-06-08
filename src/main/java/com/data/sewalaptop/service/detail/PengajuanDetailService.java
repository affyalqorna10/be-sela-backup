package com.data.sewalaptop.service.detail;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.detail.PengajuanDetailDTO;
import com.data.sewalaptop.model.detail.PengajuanDetail;
import com.data.sewalaptop.model.master.MstStock;
import com.data.sewalaptop.model.master.Pengajuan;
import com.data.sewalaptop.repository.detail.PengajuanDetailRepository;
import com.data.sewalaptop.repository.master.PengajuanRepository;
import com.data.sewalaptop.repository.master.StockRepository;
import com.data.sewalaptop.service.master.PengajuanService;
import com.data.sewalaptop.service.master.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengajuanDetailService {

    @Autowired
    private PengajuanDetailRepository pengajuanDetailRepo;

    @Autowired
    private PengajuanRepository pengajuanRepo;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private PengajuanService pengajuanService;

    @Autowired
    private StockService stockService;

    public ResponseEntity<?> savePengajuanDetail(PengajuanDetailDTO requestDTO){
        return createPengajuanDetail(requestDTO);
    }

    private ResponseEntity<?> createPengajuanDetail(PengajuanDetailDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuan1 = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuandId());
        MstStock stock = stockRepo.findByStockId(requestDTO.getStockId());

        if (pengajuan1.getPengajuanId() == null){
            response.setCode("204");
            response.setMessage("Pengjuan Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (stock.getStockId() == null) {
            response.setCode("204");
            response.setMessage("Stock Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PengajuanDetail pengajuanDetailEntity = new PengajuanDetail();
        PengajuanDetail pengajuanDetail = pengajuanDetailRepo.findByPengajuanDetailId(requestDTO.getPengajuandetailId());

        if (pengajuanDetail == null){
            pengajuanDetailEntity.setPengajuandId(requestDTO.getPengajuandId());
            pengajuanDetailEntity.setStockId(requestDTO.getStockId());
            pengajuanDetailEntity.setKeterangan(requestDTO.getKeterangan());

            pengajuanDetailRepo.save(pengajuanDetailEntity);
        }
        response.setCode("201");
        response.setData(null);
        response.setMessage("Pengajuan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<PengajuanDetail> pengajuanDetails= pengajuanDetailRepo.findAll();

        response.setCode("200");
        response.setData(pengajuanDetails);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
