import request from '@/utils/request'

// 查询论坛主题列表
export function listPost(query) {
  return request({
    url: '/bbs/post/list',
    method: 'get',
    params: query
  })
}

// 查询论坛主题详细
export function getPost(postId) {
  return request({
    url: '/bbs/post/' + postId,
    method: 'get'
  })
}

// 新增论坛主题
export function addPost(data) {
  return request({
    url: '/bbs/post',
    method: 'post',
    data: data
  })
}

// 修改论坛主题
export function updatePost(data) {
  return request({
    url: '/bbs/post',
    method: 'put',
    data: data
  })
}

// 删除论坛主题
export function delPost(postId) {
  return request({
    url: '/bbs/post/' + postId,
    method: 'delete'
  })
}
