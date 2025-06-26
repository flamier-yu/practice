import request from '@/utils/request'

// 查询论坛分类列表
export function listCategory(query) {
  return request({
    url: '/bbs/category/list',
    method: 'get',
    params: query
  })
}

// 查询论坛分类详细
export function getCategory(categoryId) {
  return request({
    url: '/bbs/category/' + categoryId,
    method: 'get'
  })
}

// 新增论坛分类
export function addCategory(data) {
  return request({
    url: '/bbs/category',
    method: 'post',
    data: data
  })
}

// 修改论坛分类
export function updateCategory(data) {
  return request({
    url: '/bbs/category',
    method: 'put',
    data: data
  })
}

// 删除论坛分类
export function delCategory(categoryId) {
  return request({
    url: '/bbs/category/' + categoryId,
    method: 'delete'
  })
}
