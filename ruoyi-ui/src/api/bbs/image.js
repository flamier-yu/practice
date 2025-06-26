import request from '@/utils/request'

// 查询论坛图片列表
export function listImage(query) {
  return request({
    url: '/bbs/image/list',
    method: 'get',
    params: query
  })
}

// 查询论坛图片详细
export function getImage(imageId) {
  return request({
    url: '/bbs/image/' + imageId,
    method: 'get'
  })
}

// 新增论坛图片
export function addImage(data) {
  return request({
    url: '/bbs/image',
    method: 'post',
    data: data
  })
}

// 修改论坛图片
export function updateImage(data) {
  return request({
    url: '/bbs/image',
    method: 'put',
    data: data
  })
}

// 删除论坛图片
export function delImage(imageId) {
  return request({
    url: '/bbs/image/' + imageId,
    method: 'delete'
  })
}
