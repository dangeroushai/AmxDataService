package com.amx.dataservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.HotelPackageDao;
import com.amx.dataservice.enums.ValuationWayEnum;
import com.amx.dataservice.exception.PriceFieldIllegalException;
import com.amx.dataservice.model.bo.PackageBo;
import com.amx.dataservice.model.domain.PackageDo;
import com.amx.dataservice.service.HotelPackageService;
import com.amx.dataservice.util.StringUtil;

@Service("hotelPackageService")
public class HotelPackageServiceImpl extends BaseService implements HotelPackageService {

	@Autowired
	private HotelPackageDao packageDao;
	
	@Override
	public List<PackageBo> findAllByProductId(Integer pid) {
		//起始价格升序
		//Sort sort = new Sort(Direction.ASC, "startPrice");
		List<PackageBo> boList = null;
		List<PackageDo> doList =  packageDao.findByProductIdOrderByStartPrice(pid);
		if(doList != null){
			boList = new ArrayList<PackageBo>();
			for(PackageDo pdo : doList){
				computeAndSaveStartPrice(pdo, false);
				boList.add(createPackageBo(pdo));
			}
		}
		
		return boList;
	}

	@Override
	public List<Integer> getIdListByProductId(long id) {
		return packageDao.getIdListByProductId(id);
	}

	@Override
	public PackageBo findOne(long id) {
		PackageDo pdo = packageDao.findOne(id);
		computeAndSaveStartPrice(pdo, false);
		return createPackageBo(pdo);
	}
	
	@Override
	public List<Integer> getProductIdListByStartPriceBetween(Integer startPrice, Integer endPrice){
		//参数错误
		if(startPrice == null && endPrice == null){
			return null;
		}
		if(startPrice == null){
			return packageDao.getProductIdListByStartPriceLessThan(endPrice);
		}
		if(endPrice == null){
			return packageDao.getProductIdListByStartPriceGreaterThan(startPrice);
		}
		
		return packageDao.getProductIdListByStartPriceBetween(startPrice, endPrice);
	}
	
	/**
	 * 计算并保存套餐的起售价
	 * @param packDo
	 * @param forceCompute 强制计算
	 */
	private void computeAndSaveStartPrice(PackageDo packDo,boolean forceCompute){
		
		if(packDo == null || (packDo.getStartPrice().doubleValue() != 0 && !forceCompute)){
			return ;
		}
		if(StringUtil.isEmpty(packDo.getAdultPriceLevel())){
			return ;
		}
		
		double startPrice = 0;
		try {
			//按人数计价
			if(packDo.getAdultValuationWay().equals(ValuationWayEnum.PRE_PERSON.getId())){
				//拆分每隔语言的价格信息
				String[] language_priceLevel = packDo.getAdultPriceLevel().split(",");
				for (int i = 0; i < language_priceLevel.length; i++) {
					//这里拆分是为了容错，事实上不应该拆分
					double laguage_startPrice = Double.parseDouble(language_priceLevel[i].split("\\|")[0]);
					if(startPrice == 0 || laguage_startPrice < startPrice){
						startPrice = laguage_startPrice;
					}
				}
			}else if(packDo.getAdultValuationWay().equals(ValuationWayEnum.LEVEL.getId())){//按梯度计
				//人数梯度
				String[] adultLevel = packDo.getAdultLevel().split("\\|");
				//每种语言的价格字符串
				String[] language_priceLevel = packDo.getAdultPriceLevel().split(",");
				for (int i = 0; i < language_priceLevel.length; i++) {
					//每种语言的价格梯度
					String[] priceLevel = language_priceLevel[i].split("\\|");
					for (int j = 0; j < priceLevel.length; j++) {
						double person_language_priceLevel = Double.parseDouble(priceLevel[j]) / Integer.parseInt(adultLevel[j]);
						if(startPrice == 0 || person_language_priceLevel < startPrice){
							startPrice = person_language_priceLevel;
						}
					}
				}
			}else{
				return ;
			}
		} catch (Exception e) {
			//设置为下线
			packDo.setSaleState(2);
			logger.error("Package[id:" + packDo.getId() + "]Price Field Is Illegal，The System Has Been Set This Package OffLine，Please Check It");
		}
		
		packDo.setStartPrice(new BigDecimal(startPrice));
		packageDao.save(packDo);
	}
	
	/**
	 * 根据数据对象生成一个新的业务对象
	 * @param pdo
	 * @return
	 */
	private PackageBo createPackageBo(PackageDo pdo){
		if(pdo == null) {
			return null;
		}
		PackageBo packageBo = new PackageBo();
		
		/*拷贝基本属性*/
		BeanUtils.copyProperties(pdo, packageBo);
		try {
			/* 处理价格信息  - START*/
			/* 成人 */
			if(pdo.getAdultValuationWay().equals(ValuationWayEnum.PRE_PERSON.getId())){//按人数
				/*this.adultPricePerPersonList = new ArrayList<Double>();
				//成人单价-按语言排序
				for(String aPrice : pdo.getAdultPriceLevel().split(",")){
					this.adultPricePerPersonList.add(Double.parseDouble(aPrice));
				}
				//成人单人预付
				if(!"".equals(pdo.getObAdultPriceLevel().trim())){
					//成人单价-按语言排序
					for(String aObPrice : pdo.getObAdultPriceLevel().split(",")){
						this.adultObligationPricePerPersonList.add(Double.parseDouble(aObPrice));
					}
				}*/
				List<ArrayList<Double>> adultPriceList = generatePrice(pdo.getAdultPriceLevel(), pdo.getObAdultPriceLevel());
				if(adultPriceList != null){
					packageBo.setAdultPricePerPersonList(adultPriceList.get(0));
					packageBo.setAdultObligationPricePerPersonList(adultPriceList.get(1));
				}
			}else{//2-按区间
				List<List<TreeMap<Integer,Double>>> adultPriceLevelList = generatePriceLevel(pdo.getAdultLevel(),pdo.getAdultPriceLevel(),pdo.getObAdultPriceLevel());
				if(adultPriceLevelList != null){
					packageBo.setAdultPriceLevelList(adultPriceLevelList.get(0));
					packageBo.setAdultObligatioPriceLevelList(adultPriceLevelList.get(1));
				}
			}
			/* 儿童  */
			if(pdo.getChildValuationWay().equals(ValuationWayEnum.PRE_PERSON.getId())){//按人数
				List<ArrayList<Double>> childPriceList = generatePrice(pdo.getChildPriceLevel(), pdo.getObChildPriceLevel());
				if(childPriceList != null){
					packageBo.setChildPricePerPersonList(childPriceList.get(0));
					packageBo.setChildObligationPricePerPersonList(childPriceList.get(1));
				}
			}else if(pdo.getChildValuationWay().equals(ValuationWayEnum.LEVEL.getId())){//2-按区间
				List<List<TreeMap<Integer,Double>>> childPriceLevelList = generatePriceLevel(pdo.getChildLevel(),pdo.getChildPriceLevel(),pdo.getObChildPriceLevel());
				if(childPriceLevelList != null){
					packageBo.setChildPriceLevelList(childPriceLevelList.get(0));
					packageBo.setChildObligatioPriceLevelList(childPriceLevelList.get(1));
				}
			}else{/*按成人*/}
			/* 处理价格信息  - END*/
		} catch (Exception e) {
			logger.error("Package[id:" + pdo.getId() + "]", e);
		}
		
		return packageBo;
	}

	/**
	 * 按人数计价时，将原始的字符串处理为包含价格信息的List
	 * @param priceLevelStr 数据库中原始的价格字符串
	 * @param obPriceLevelStr 数据库中原始的待付价格字符串（可能为空）
	 * @return List大小固定为二;List[0]-单人价格;List[1]-单人待付价格（当非待付产品时为null）;
	 */
	private List<ArrayList<Double>> generatePrice(String priceLevelStr, String obPriceLevelStr){
		if(StringUtil.isEmpty(priceLevelStr)){
			return null;
		}
		
		//this.adultPricePerPersonList
		ArrayList<Double> pricePerPersonList = new ArrayList<Double>();
		//成人(儿童)单价-按语言排序
		for(String aPrice : priceLevelStr.split(",")){
			//this.adultPricePerPersonList
			try {
				pricePerPersonList.add(Double.parseDouble(aPrice));
			} catch (Exception e) {
				// 数据格式有误(本应按人数计价，存储为了按梯度计价格式)=》解析出错
				pricePerPersonList.add(Double.parseDouble(aPrice.split("\\|")[0]));
			}
		}
		//成人(儿童)单人预付
		ArrayList<Double> obligationPricePerPersonList = null;
		if(!StringUtil.isEmpty(obPriceLevelStr)){
			obligationPricePerPersonList = new ArrayList<Double>();
			for(String aObPrice : obPriceLevelStr.split(",")){
				//this.adultObligationPricePerPersonList
				obligationPricePerPersonList.add(Double.parseDouble(aObPrice));
			}
		}
		
		List<ArrayList<Double>> priceListArr = new ArrayList<ArrayList<Double>>();
		
		priceListArr.add(0, pricePerPersonList);
		priceListArr.add(1, obligationPricePerPersonList);
		
		return priceListArr;
	}
	
	/**
	 * 按人数梯度计价时，将原始的字符串处理为包含价格信息的List
	 * @param personLevelStr 数据库中原始的人数梯度字符串
	 * @param priceLevelStr 数据库中原始的价格梯度字符串
	 * @param obligationPriceLevelStr 数据库中原始的待付价格梯度字符串（可能为空）
	 * @return List大小固定为二;List[0]-价格梯度;List[1]-待付价格梯度（当非待付产品时为null）;
	 */
	private List<List<TreeMap<Integer,Double>>> generatePriceLevel(String personLevelStr , String priceLevelStr , String obligationPriceLevelStr ){
		if(StringUtil.isEmpty(personLevelStr) || StringUtil.isEmpty(priceLevelStr)){
			return null;
		}
		
		//待付标记
		boolean isObligation = false;
		//拆分每个计价梯度的最大人数
		String[] personLevel = personLevelStr.split("\\|");
		//按语言排序的价格梯度信息
		List<TreeMap<Integer,Double>> priceLevelList = new ArrayList<TreeMap<Integer,Double>>();
		//按语言排序的待付价格梯度信息
		List<TreeMap<Integer,Double>> obligationPriceLevelList = null;
		//拆分每个语言的计价梯度
		String[] langPriceLevelArray = priceLevelStr.split(",");
		//每个语言的待付价格梯度
		String[] langObligationPriceLevelArray = null;
		//含待付信息
		if(!StringUtil.isEmpty(obligationPriceLevelStr)){
			//拆分每个语言的待付价格梯度
			langObligationPriceLevelArray = obligationPriceLevelStr.split(",");
			//检查是否是待付产品
			if(langPriceLevelArray != null && langObligationPriceLevelArray != null && langPriceLevelArray.length == langObligationPriceLevelArray.length){
				isObligation = true;
				obligationPriceLevelList = new ArrayList<TreeMap<Integer,Double>>();
			}
		}
		if(langPriceLevelArray != null){
			//遍历每个语言的计价梯度
			for(int i = 0; i < langPriceLevelArray.length ; i++){
				String langPriceLevel = langPriceLevelArray[i];
				//人数梯度索引
				int personLevel_i = 0;
				//<人数，人数对应的总价格>
				TreeMap<Integer,Double> priceLevelTreeMap = new TreeMap<Integer,Double>();
				//遍历当前语言的价格梯度
				String[] lang_PersonALLPriceArray = langPriceLevel.split("\\|");
				for(int j = 0; j < personLevel.length ; j++){
					String lang_PersonALLPrice = lang_PersonALLPriceArray[j];
					try{
						//当前梯度适用总人数
						Integer currentLevelPersonNum = Integer.parseInt(personLevel[personLevel_i++]);
						//当前梯度人数总价格
						Double currentLevelPersonNumTotalPrice = Double.parseDouble(lang_PersonALLPrice);
						//对应人数的对应价格
						priceLevelTreeMap.put(currentLevelPersonNum, currentLevelPersonNumTotalPrice);
					}catch (Exception e) {
						throw new PriceFieldIllegalException("The Field priceLevel Is Illegal:" + lang_PersonALLPrice, e);
					}
				}
				priceLevelList.add(priceLevelTreeMap);
				
				//处理待付信息
				if(isObligation){
					TreeMap<Integer,Double> obligationPriceLevelTreeMap = new TreeMap<Integer,Double>();
					personLevel_i = 0;
					String langObligationPriceLevel = langObligationPriceLevelArray[i];
					//遍历当前语言的待付价格梯度
					String[] lang_PersonALLObligationPriceArray = langObligationPriceLevel.split("\\|");
					if(lang_PersonALLObligationPriceArray.length == personLevel.length){
						for(int j = 0; j < personLevel.length ; j++){
							String lang_PersonALLObligationPrice = lang_PersonALLObligationPriceArray[j];
							//当前梯度适用总人数
							Integer currentLevelPersonNum = Integer.parseInt(personLevel[personLevel_i++]);
							//当前梯度人数总待付价格
							Double currentLevelPersonNumTotalObligationPrice = Double.parseDouble(lang_PersonALLObligationPrice);
							//对应人数的对应待付价格
							obligationPriceLevelTreeMap.put(currentLevelPersonNum, currentLevelPersonNumTotalObligationPrice);
						}
					}else{						
						throw new PriceFieldIllegalException("The Field priceLevel["+ priceLevelStr +"]Dones't Match The Field obligationPriceLevel["+ obligationPriceLevelStr +"]");
					}
						
					obligationPriceLevelList.add(obligationPriceLevelTreeMap);
				}
			}
		}
		
		List<List<TreeMap<Integer,Double>>> priceLevelListArr = new ArrayList<List<TreeMap<Integer,Double>>>();
		
		priceLevelListArr.add(0, priceLevelList);
		priceLevelListArr.add(1, obligationPriceLevelList);
		
		return priceLevelListArr;
	}
	
}
