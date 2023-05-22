CREATE INDEX mst_brands_brand_id_idx ON public.mst_brands USING btree (brand_id, brand_name);
CREATE INDEX mst_spesifikasi_spek_id_idx ON public.mst_spesifikasi USING btree (spek_id, brand_id);
CREATE INDEX mst_stock_stock_id_idx ON public.mst_stock USING btree (stock_id, brand_id, code_qr);

CREATE OR replace view trx_detail_stock
as
select mb.brand_id,
       mb.brand_name,
       ms."storage",
       ms.processor,
       ms.ram,
       ms.graphic_card,
       count(mst.brand_id) AS stock
from mst_brands mb
         join mst_spesifikasi ms on mb.brand_id = ms.brand_id
         join mst_stock mst on mb.brand_id = mst.brand_id
GROUP BY mb.brand_id, ms."storage", ms.processor, ms.ram, ms.graphic_card;

CREATE OR replace view trx_customer_stock
as
select mst.stock_id as id,
       mst.brand_id as brand_id,
       mst.code_qr as barcode,
       ms.spek_id as spesifikasi,
       mst.status as status,
       'Nama Customer' AS customer
from mst_stock mst
         join mst_brands mb on mst.brand_id = mb.brand_id
         join mst_spesifikasi ms on mb.brand_id = ms.brand_id;