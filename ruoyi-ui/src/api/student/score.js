import request from '@/utils/request'

// 查询分数列表
export function listScore(query) {
  return request({
    url: '/student/score/list',
    method: 'get',
    params: query
  })
}

// 查询分数详细
export function getScore(scoreId) {
  return request({
    url: '/student/score/' + scoreId,
    method: 'get'
  })
}

// 新增分数
export function addScore(data) {
  return request({
    url: '/student/score',
    method: 'post',
    data: data
  })
}

// 修改分数
export function updateScore(data) {
  return request({
    url: '/student/score',
    method: 'put',
    data: data
  })
}

// 删除分数
export function delScore(scoreId) {
  return request({
    url: '/student/score/' + scoreId,
    method: 'delete'
  })
}
