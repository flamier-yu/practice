import request from '@/utils/request'

// 查询点赞列表
export function listLike(query) {
  return request({
    url: '/bbs/like/list',
    method: 'get',
    params: query
  })
}

// 查询点赞详细
export function getLike(likeId) {
  return request({
    url: '/bbs/like/' + likeId,
    method: 'get'
  })
}

// 新增点赞
export function addLike(data) {
  return request({
    url: '/bbs/like',
    method: 'post',
    data: data
  })
}

// 修改点赞
export function updateLike(data) {
  return request({
    url: '/bbs/like',
    method: 'put',
    data: data
  })
}

// 删除点赞
export function delLike(likeId) {
  return request({
    url: '/bbs/like/' + likeId,
    method: 'delete'
  })
}
