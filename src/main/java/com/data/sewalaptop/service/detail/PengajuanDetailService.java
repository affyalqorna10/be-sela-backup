package com.data.sewalaptop.service.detail;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.detail.PengajuanDetailDTO;
import com.data.sewalaptop.model.detail.PengajuanDetail;
import com.data.sewalaptop.model.master.MstBrands;
import com.data.sewalaptop.model.master.MstSpesifikasi;
import com.data.sewalaptop.model.master.MstStock;
import com.data.sewalaptop.model.master.Pengajuan;
import com.data.sewalaptop.repository.detail.PengajuanDetailRepository;
import com.data.sewalaptop.repository.master.BrandRepository;
import com.data.sewalaptop.repository.master.PengajuanRepository;
import com.data.sewalaptop.repository.master.SpesificationRepository;
import com.data.sewalaptop.repository.master.StockRepository;
import com.data.sewalaptop.service.master.BrandService;
import com.data.sewalaptop.service.master.PengajuanService;
import com.data.sewalaptop.service.master.SpesificationService;
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
    private BrandRepository brandRepo;

    @Autowired
    private SpesificationRepository spesificationRepo;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private PengajuanService pengajuanService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SpesificationService spesificationService;

    @Autowired
    private StockService stockService;

    public ResponseEntity<?> savePengajuanDetail(PengajuanDetailDTO requestDTO){
        if (requestDTO.getPengajuandetailId() == null){
            return createPengajuanDetail(requestDTO);
        }
        return updatePengajuanDetail(requestDTO);
    }

    private ResponseEntity<?> createPengajuanDetail(PengajuanDetailDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuandId());
        MstBrands brands = brandRepo.findByBrandId(requestDTO.getBrandId());
        MstSpesifikasi spesifikasi = spesificationRepo.findBySpekId(requestDTO.getSpekId());
        MstStock stock = stockRepo.findByStockId(requestDTO.getStockId());

        if (pengajuan.getPengajuanId() == null){
            response.setCode("204");
            response.setMessage("Pengjuan Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (brands.getBrandId() == null) {
            response.setCode("204");
            response.setMessage("Stock Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (spesifikasi.getSpekId() == null) {
            response.setCode("204");
            response.setMessage("Stock Id cannot be empty");
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
            pengajuanDetailEntity.setBrandId(requestDTO.getBrandId());
            pengajuanDetailEntity.setSpekId(requestDTO.getSpekId());
            pengajuanDetailEntity.setStockId(requestDTO.getStockId());

            pengajuanDetailRepo.save(pengajuanDetailEntity);
        }
        response.setCode("201");
        response.setData(null);
        response.setMessage("Pengajuan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> updatePengajuanDetail(PengajuanDetailDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        PengajuanDetail pengajuanDetailEntity = new PengajuanDetail();
        PengajuanDetail pengajuanDetailList = pengajuanDetailRepo.findByPengajuanDetailId(requestDTO.getPengajuandetailId());

        if (pengajuanDetailList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PengajuanDetail pengajuanDetail = pengajuanDetailRepo.findByPengajuanDetailId(requestDTO.getPengajuandetailId());
        if (requestDTO.getPengajuandetailId() == null) {
            pengajuanDetailEntity.setPengajuandetailId(pengajuanDetail.getPengajuandetailId());
        }else{
            pengajuanDetailEntity.setPengajuandetailId(requestDTO.getPengajuandetailId());
        }

        if (requestDTO.getBrandId() == null){
            pengajuanDetailEntity.setBrandId(pengajuanDetail.getBrandId());
        }else{
            pengajuanDetailEntity.setBrandId(requestDTO.getBrandId());
        }

        if (requestDTO.getSpekId() == null){
            pengajuanDetailEntity.setSpekId(pengajuanDetail.getSpekId());
        }else{
            pengajuanDetailEntity.setSpekId(requestDTO.getSpekId());
        }

        if (requestDTO.getStockId() == null){
            pengajuanDetailEntity.setStockId(pengajuanDetail.getStockId());
        }else {
            pengajuanDetailEntity.setStockId(requestDTO.getStockId());
        }

        pengajuanDetailRepo.save(pengajuanDetailEntity);
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

    public ResponseEntity<?> getPengajuanDetailId(Long pengajuanDetailId){
        ResponseDTO response = new ResponseDTO();
        PengajuanDetail pengajuanDetail = pengajuanDetailRepo.findByPengajuanDetailId(pengajuanDetailId);

        response.setCode("200");
        response.setData(pengajuanDetail);
        response.setMessage("Get Data by Pengajuan Detail Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePengajuan(Long pengajuanDetailId){
        ResponseDTO response = new ResponseDTO();
        PengajuanDetail pengajuanDetail = pengajuanDetailRepo.findByPengajuanDetailId(pengajuanDetailId);
        if (pengajuanDetail == null){
            response.setCode("204");
            response.setMessage("Pengajuan Detail ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        pengajuanDetailRepo.delete(pengajuanDetail);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Pengajuan Detail Id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
